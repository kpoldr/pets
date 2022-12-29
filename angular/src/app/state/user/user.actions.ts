import { createAction, props} from '@ngrx/store';
import { IJWTResponse } from 'src/app/domain/IJWTResponse';


export const updateUser = createAction(
    '[Login Page] Update User',
    props<{ content: IJWTResponse}>()
);

export const removeUser = createAction(
    '[Login Page] remove User'
);