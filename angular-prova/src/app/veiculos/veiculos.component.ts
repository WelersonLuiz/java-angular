import { Component, OnInit } from '@angular/core';
import { Veiculo } from 'model/veiculo';
import { ApiService } from 'app/api.service';

@Component({
	selector: 'app-veiculos',
	templateUrl: './veiculos.component.html',
	styleUrls: ['./veiculos.component.scss']
})
export class VeiculosComponent implements OnInit {  

	constructor(private _api: ApiService) { }
	ngOnInit() {
	}

	idVeiculo;
	
	onKey(value: string) {
		this.idVeiculo = value;
	}
	
	dataSource: Veiculo;
	getVeiculo() {
		this._api.getVeiculo(this.idVeiculo).subscribe(
			res => {
				this.dataSource = res;
				console.log(this.dataSource);
			}
		);
	}

}
