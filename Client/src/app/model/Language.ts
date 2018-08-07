export class Language {
    name: string;
    pathToIconFile: string;

    constructor(name:string, path: string){
        this.name = name;
        this.pathToIconFile = path;
    }

    getName(){
        return this.name;
    }
}