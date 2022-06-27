import Search from "@mui/icons-material/Search";
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { SearchTarget } from "../components/SearchResult";

export type SearchState = {
    isSearch: boolean,
    searchTarget: SearchTarget,
    data: any
}

const initialState: SearchState = {
    isSearch: false,
    searchTarget: SearchTarget.ACTOR,
    data: '',
}

export const searchSlice = createSlice({
    name: 'search/search',
    initialState,
    reducers: {
        setSearch: (state, action) => {
            state.isSearch = action.payload;
        },
        setTarget: (state, action) => {
            state.searchTarget = action.payload;
        },
        setData: (state, action) => {
            state.data = action.payload._embedded;
        },
        resetData: (state) => {
            state.data = '';
        },
    }
});

export const {
    setSearch,
    setTarget,
    setData,
    resetData,
} = searchSlice.actions;
export default searchSlice.reducer;