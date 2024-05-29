import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { valoracion } from '../../model/valoracion';

@Injectable({
  providedIn: 'root'
})
export class ValoracionService {

  baseUrl = 'http://localhost:8080/api/v1/valoracion';

  constructor(private http: HttpClient) { }

  getValoraciones():Observable<any>{
    return this.http.get<any>(this.baseUrl)
  }

  getValoracionByIdById(id:number):Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/${id}`)
  }

  getValoracionesByIdByUsuario(id:number):Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/${id}`)
  }

  getValoracionesByIdByArticulo(id:number):Observable<any>{
    return this.http.get<any[]>(`${this.baseUrl}/valoraciones?articulo=${id}`);
  }

  postValoracion(valoracion: valoracion):Observable<any>{
    return this.http.post(this.baseUrl,valoracion)
  }

  editarValoracionPorId(id:number, valoracion: valoracion):Observable<any>{
    return this.http.put(this.baseUrl,valoracion)
  }
  
}