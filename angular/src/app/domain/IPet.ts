import { IPetColor } from "./IPetColor";
import { IPetCountry } from "./IPetCountry";
import { IPetType } from "./IPetType";

export interface IPet {
    id?: number;
    name: string;
    idCode: string;
    color: string;
    type: string;
    country: string;
}