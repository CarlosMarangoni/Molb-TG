package com.carlos.costura.domain.service;

import com.carlos.costura.domain.exception.ConflictException;
import com.carlos.costura.domain.exception.PageNotFoundException;
import com.carlos.costura.domain.model.*;
import com.carlos.costura.domain.model.dto.CartForm;
import com.carlos.costura.domain.model.dto.CartItem;
import com.carlos.costura.domain.model.enumeration.PaymentMethod;
import com.carlos.costura.domain.model.pk.PostItemPK;
import com.carlos.costura.domain.model.pk.SaleItemPK;
import com.carlos.costura.domain.repository.CartRepository;
import com.carlos.costura.domain.repository.PostItemRepository;
import com.carlos.costura.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostItemRepository postItemRepository;

    public boolean save(CartForm cartForm) {
        User user = User.isAuthenticatedReturnUser();
        List<PostItem> postItemList = new ArrayList<>();
        BigDecimal valorTotal = BigDecimal.ZERO;
        Purchase purchase = Purchase.toModel(cartForm);
        purchase.setBuyer(user);
        for (CartItem i : cartForm.getItems()) {
            Post post = postRepository.findById(i.getPostId()).orElseThrow(() -> new PageNotFoundException("Post não encontrado."));
            if(post.getUser().getId().equals(user.getId())){
                throw new ConflictException("Você é o dono do post "+ post.getId() + ", então não pode comprá-lo.");
            }

            boolean exists = postItemRepository.existsByPostItemPK(new PostItemPK(post,i.getItem()));
            if (exists){
                var postItem = PostItem.builder().postItemPK(new PostItemPK(post,i.getItem())).build();
                postItemList.add(postItem);
            }else{
                throw new PageNotFoundException("Item não encontrado.");
            }
        }

        for (PostItem i : postItemList) {
            var postItem = postItemRepository.findById(i.getPostItemPK()).get();
            valorTotal = valorTotal.add(postItem.getPrice());
        }

        purchase.getItems().forEach(i ->{
            i.getSaleItemPK().setPurchase(purchase);
        });
        purchase.setTotal(valorTotal);
        purchase.setPaymentMethod(PaymentMethod.PAYPAL);
        return userService.buy(purchase,user);
    }
}
