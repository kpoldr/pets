import { IPetColor } from "./IPetColor";
import { IPetCountry } from "./IPetCountry";
import { IPetType } from "./IPetType";

export interface IPetExtra {
    types: IPetType[];
    colors: IPetColor[];
    countries: IPetCountry[];
}