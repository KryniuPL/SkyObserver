export class Currency {
    name: string;
    pathToIconFile: string;

    constructor(name:string, path: string){
        this.name = name;
        this.pathToIconFile = path;
    }
}
