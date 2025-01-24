import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';


@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

  private _keycloak: Keycloak | undefined;



  constructor() { }

  get keycloak(){
    if(!this._keycloak){
      this._keycloak = new Keycloak({
        url: 'http://localhost:9090',
        realm : 'messaging-app',
        clientId : 'messaging-app'
      })
    }
    return this._keycloak;
  }

  async init(){
    const authenticated = await this.keycloak.init({
      onLoad: 'login-required'
    })
  }




}
