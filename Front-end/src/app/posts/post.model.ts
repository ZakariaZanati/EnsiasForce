export class PostModel {
    id : number;
    description : string;
    publisherFullName : string;
    profileImage?: any;
    duration?: any;
    image?: any;
    likeCount : number;
    commentCount : number;
    liked : boolean;
    dateCreation : Date;
    iscommentSectionShowed?: boolean;
    
}

export class postRequestModel{
    userID : number;
    description: string;
    image?: any;
}