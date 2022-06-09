import { Component, OnInit, ViewChild } from '@angular/core';
import { ChartConfiguration, ChartEvent, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { PostService } from '../service/post.service';
import { TokenStorageService } from '../service/token-storage.service';

@Component({
  selector: 'app-dashboard-test',
  templateUrl: './dashboard-test.component.html',
  styles: [
  ]
})
export class DashboardTestComponent implements OnInit {
 

  panelOpenState = false;
  selectedIndex: number = 0;
  years: string[] = ['2020','2021','2022']

  public lineChartData: ChartConfiguration['data'] = {
    datasets: [
      {
        data: [ 65, 59, 80, 81, 56, 55, 40,50,60,70,0o3,20 ], //vendas
        label: 'Quantidade de Vendas',
        backgroundColor: 'rgba(148,159,177,0.2)',
        borderColor: 'rgba(148,159,177,1)',
        pointBackgroundColor: 'rgba(148,159,177,1)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgba(148,159,177,0.8)',
        fill: 'origin',
      }
    ],
    labels: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho','Agosto','Setembro','Outubro','Novembro','Dezembro']
  };

  public lineChartOptions: ChartConfiguration['options'] = {
    elements: {
      line: {
        tension: 0.5
      }
    },
    scales: {
      // We use this empty structure as a placeholder for dynamic theming.
      x: {},
      'y-axis-0':
        {
          position: 'left',
        },
      'y-axis-1': {
        position: 'right',
        grid: {
          color: 'rgba(255,0,0,0.3)',
        },
        ticks: {
          color: 'red'
        }
      }
    },

    plugins: {
      legend: { display: true }
    }
  };

  public lineChartType: ChartType = 'line';

  constructor(private postService:PostService,private token: TokenStorageService){

  }

 /* @ViewChild(BaseChartDirective) chart?: BaseChartDirective;

  private static generateNumber(i: number): number {
    return Math.floor((Math.random() * (i < 2 ? 100 : 1000)) + 1);
  }

  public randomize(): void {
    for (let i = 0; i < this.lineChartData.datasets.length; i++) {
      for (let j = 0; j < this.lineChartData.datasets[i].data.length; j++) {
        this.lineChartData.datasets[i].data[j] = DashboardTestComponent.generateNumber(i);
      }
    }
    this.chart?.update();
  }

  // events
  public chartClicked({ event, active }: { event?: ChartEvent, active?: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({ event, active }: { event?: ChartEvent, active?: {}[] }): void {
    console.log(event, active);
  }

  public pushOne(): void {
    this.lineChartData.datasets.forEach((x, i) => {
      const num = DashboardTestComponent.generateNumber(i);
      x.data.push(num);
    });
    this.lineChartData?.labels?.push(`Label ${ this.lineChartData.labels.length }`);

    this.chart?.update();
  }
  */
  setIndex(index: number) {
    this.selectedIndex = index;
    /*if(index!=0){
      this.postService.obterPostsPorCategoria(this.categories[index].name.toLowerCase()).subscribe(posts =>{
        this.posts = posts;
        console.log(posts)
      },error => console.log(error))
    }else{
      this.postService.obterTodasPostagensPaginadas(this.activePage)
    .subscribe(posts =>{
      this.posts = posts;
      console.log(posts)
    },
    error => console.log(error))
    }*/
 }

 ngOnInit(): void {
  for (let i = 0; i < this.lineChartData.datasets.length; i++) {
    this.lineChartData.datasets[i].data = [2,1]   
  
  
}
}


  
}
