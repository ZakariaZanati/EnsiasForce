import {JobPayload} from './job.payload';

export class JobsResponse {

    jobs : Array<JobPayload>;
    totalPages : number;
    pageNumber : number;
    pageSize : number;
}