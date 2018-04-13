//
// ANZ Project for an Interview
// 
// Equity Data Signal App By Mostafa Farshchi
// Template pack-angular:web/src/app/entities/entity.ts.e.vm
//

export class User {
    // Raw attributes
    id : number;
    login : string;
    password : string;
    email : string;
    isEnabled : boolean;
    civility : string;
    countryCode : string;
    firstName : string;
    lastName : string;
    creationDate : Date;
    creationAuthor : string;
    lastModificationDate : Date;
    lastModificationAuthor : string;
    version : number;


    constructor(json? : any) {
        if (json != null) {
            this.id = json.id;
            this.login = json.login;
            this.password = json.password;
            this.email = json.email;
            this.isEnabled = json.isEnabled;
            this.civility = json.civility;
            this.countryCode = json.countryCode;
            this.firstName = json.firstName;
            this.lastName = json.lastName;
            if (json.creationDate != null) {
                this.creationDate = new Date(json.creationDate);
            }
            this.creationAuthor = json.creationAuthor;
            if (json.lastModificationDate != null) {
                this.lastModificationDate = new Date(json.lastModificationDate);
            }
            this.lastModificationAuthor = json.lastModificationAuthor;
            this.version = json.version;
        }
    }

    // Utils

    static toArray(jsons : any[]) : User[] {
        let users : User[] = [];
        if (jsons != null) {
            for (let json of jsons) {
                users.push(new User(json));
            }
        }
        return users;
    }
}
