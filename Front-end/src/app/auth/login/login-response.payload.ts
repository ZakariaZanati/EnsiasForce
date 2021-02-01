export class LoginResponsePayload {
    authenticationToken : string;
    refreshToken: string;
    expiresAt : Date;
    email : string;
    completed : boolean;
    fullName : string;
    userType : string;
}