export class Currency {
    name: string;
    pathToIconFile: string;
    symbol:string;

    constructor(name:string,symbol:string, path: string){
        this.name = name;
        this.pathToIconFile = path;
        this.symbol = symbol;
    }

    getName(){
        return this.name;
    }
}
