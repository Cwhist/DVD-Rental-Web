import React from 'react'
import axios, { AxiosResponse } from 'axios';
import { SearchTarget } from '../components/SearchResult';
import * as searchSlice from '../reducers/searchSlice';


export const doSearch = (dispatch: any, target: SearchTarget, keyword: string) => {

    const requestURI = target === SearchTarget.ACTOR ? '/api/actors/' + keyword 
                    : target === SearchTarget.FILM ? '/api/films/' + keyword 
                    : target === SearchTarget.ACTOR_FILM ? '/api/films/actor-id/' + keyword : 'api/inventories/' + keyword;

    return new Promise((resolve, reject) => {
        axios
            .get(requestURI)
            .then((response: AxiosResponse) => {
                resolve(response.data);
                //dispatch(searchSlice.setTarget(target));
                if(target === SearchTarget.ACTOR || target === SearchTarget.FILM)
                    dispatch(searchSlice.setData(response.data));
                dispatch(searchSlice.setSearch(true));
            })
            .catch((response: AxiosResponse) => {
                reject();
                alert("검색어를 확인해주세요!");
            });
    });

};