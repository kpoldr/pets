import jwt_decode from 'jwt-decode';

interface decodedJwt {

    sub: string
    roles: string[]

}

export const decodeJwtToUser = (token: string) =>  {
  
    try {
      let jwt = jwt_decode(token) as decodedJwt;
        
      return { username: jwt.sub, roles: jwt.roles };
    } catch (Error) {
      return null;
    }
  }
