export class CommentModel {
    id : number;
    text : string;
    createdDate : Date;
    fullName? : string;
}


export class commentRequestModel {
    text : string;
}