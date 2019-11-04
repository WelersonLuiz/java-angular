import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { VeiculosComponent } from './veiculos/veiculos.component'
import { ViagemNovoComponent } from './viagem-novo/viagem-novo.component';
import { ViagemFimComponent } from './viagem-fim/viagem-fim.component';
import { ExtratoComponent } from './extrato/extrato.component';

const routes: Routes = [
  {
    path: 'veiculos',
    component: VeiculosComponent,
    data: { title: 'Detalhe do Veiculo'}
  },  
  {
    path: 'viagem-novo',
    component: ViagemNovoComponent,
    data: { title: 'Iniciar Viagem' }
  },
  {
    path: 'viagem-fim',
    component: ViagemFimComponent,
    data: { title: 'Finalizar Viagem'}
  },
  {
    path: 'movimentacao',
    component: ExtratoComponent,
    data: { title: 'Movimentação do Veiculo'}
  },
  {
    path: '',
    children: []
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule { }
