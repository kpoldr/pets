import { createReducer, on } from "@ngrx/store";
import { IJWTResponse } from "src/app/domain/IJWTResponse";
import { updateUser } from "./user.actions";


export interface UserState {
    jwt: IJWTResponse
    error?: string,
    status: 'pending' | 'loading' | 'error' | 'success';
}

const initialIJWTstate: IJWTResponse = {
    username: '',
    accessToken: '',
    refreshToken: '',
};

export const initialUserState: UserState = {
    jwt: initialIJWTstate,
    error: undefined,
    status: "pending"
}

export const userReducer = createReducer(
    initialUserState,
    on(updateUser, (state, {content}) => ({
        ...state,
        jwt: content
    }))
    )