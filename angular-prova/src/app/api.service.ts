import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, tap, map } from 'rxjs/operators';
import { Veiculo } from 'model/veiculo';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const apiUrl = 'http://localhost:8080/v1/aluguel/';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }


  getVeiculo (idVeiculo): Observable<Veiculo> {
    return this.http.get<Veiculo>(apiUrl.concat(idVeiculo));
  }
}
