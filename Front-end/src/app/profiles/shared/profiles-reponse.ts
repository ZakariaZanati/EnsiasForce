import { ProfilePayload} from './profile.payload';

export class ProflesResponse {
    users : Array<ProfilePayload>;
    totalPages : number;
    pageNumber : number;
    pageSize : number;
}