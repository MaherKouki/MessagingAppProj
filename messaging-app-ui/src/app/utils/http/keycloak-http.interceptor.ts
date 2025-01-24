import {HttpHeaders, HttpInterceptorFn} from '@angular/common/http';
import {inject} from "@angular/core";
import {KeycloakService} from "../keycloak/keycloak.service";

export const keycloakHttpInterceptor: HttpInterceptorFn = (req, next) => {

  const keycloakService = inject(KeycloakService);
  const token = keycloakService.keycloak.token;
  if(token) {
    const authReq = req.clone({
      headers: new HttpHeaders({
        Authentication: `Bearer ${token}`
      })
    });
    return next(authReq);
  }

  return next(req);
};
