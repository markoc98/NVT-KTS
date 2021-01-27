export interface CuluralOfferModel {
    "id" : number,
    "name" : string,
    "images" : Array<object>,
    "location" : string,
    "description" : string,
    "avgRating" : number
}

export interface CulturalOfferFullModel{
    "id": number,
    "location": string,
    "name": string,
    "description": string,
    "rating": number,
    "categoryType": {
        "name": string,
        }
}

export interface ContentCultOff{
    "content" : CulturalOfferFullModel[];
}