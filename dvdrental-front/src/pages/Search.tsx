import { FormControl, IconButton, InputLabel, MenuItem, Select, TextField } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import { Link } from 'react-router-dom';
import axios, { AxiosResponse } from 'axios';
import SearchResult, { SearchTarget } from '../components/SearchResult';
import { useDispatch, useSelector } from 'react-redux';
import * as searchSlice from '../reducers/searchSlice';
import { doSearch } from '../api/search';
import { useState } from 'react';
import StyledButton from '../components/StyledButton';

const Search = () => {

    const dispatch = useDispatch();

    const [keyword, setKeyword] = useState('');
    const isSearch: boolean = useSelector<searchSlice.SearchState>(state => state.isSearch) as boolean;
    const target: SearchTarget = useSelector<searchSlice.SearchState>(state => state.searchTarget) as SearchTarget;
    const data: any = useSelector<searchSlice.SearchState>(state => state.data);
    
    

    return (
        <div style={{ marginTop: '3rem', textAlign: 'center' }}>
            <FormControl 
                component="fieldset" 
                style= {{ margin: '0 1rem 0 1rem' }}
            >
                <InputLabel>검색대상</InputLabel>
                <Select
                    id="search_target_select"
                    label="검색대상"
                    value={target}
                    onChange={(e: any) => {
                        dispatch(searchSlice.setTarget(e.target.value));
                        dispatch(searchSlice.setSearch(false));
                    }}
                    style={{
                        width: '6rem',
                        height: '40px'
                    }}
                >
                    <MenuItem value={SearchTarget.ACTOR}>배우</MenuItem>
                    <MenuItem value={SearchTarget.FILM}>영화</MenuItem>
                </Select>
            </FormControl>
            <TextField 
                id="search_keyword" 
                label="검색어" 
                variant="outlined" 
                size="small" 
                onChange={(e) => setKeyword(e.target.value) }
            />
            <IconButton onClick={() => doSearch(dispatch, target, keyword as string)} style={{ marginLeft: '0.3rem', marginBottom: '1.5rem' }} >
                <SearchIcon />
            </IconButton>
            {isSearch && (<SearchResult target={target} data={data} />)}
            <br/><Link to="/"><StyledButton>홈으로</StyledButton></Link>
        </div>
    );
}

export default Search;