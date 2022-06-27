import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios, { AxiosResponse } from 'axios';
import StyledButton from '../components/StyledButton';
import { TextField } from '@mui/material';
import styled from 'styled-components';

const ReturnButton = styled.button`
    width: 60px;
    height: 40px;
    border-radius: 0.3rem;
    font-family: 'Noto Sans KR';
    font-size: 16px;
    font-weight: bold;
    color: #7a71b0;
    background: linear-gradient(45deg, #b8e4ff 0%, #a6b6f5 50%, #938ae3 100%);
    border: 2px solid #7a71b0;

    display: inline-block;
    margin-left: 0.5rem;

    cursor: pointer;
`;

const Return = () => {

    const [id, setId] = useState(-1);

    const returnFilmById = () => {
        
        return new Promise((resolve, reject) => {
            axios
                .post('/api/rental/return', {rentalId: id})
                .then((response: AxiosResponse) => {
                    resolve(response.data);
                    alert("대여번호 " + id + "번 " + response.data.returnDate + "에 반납되었습니다!");
                })
                .catch((response: AxiosResponse) => {
                    reject();
                    alert("반납에 실패하셨습니다! 대여번호를 다시 확인해주세요!");
                });
        });
    }

    return (
        <div style={{ marginTop: '3rem', textAlign: 'center' }}>
            <p>
                <TextField 
                    id="search_keyword" 
                    label="대여번호 입력" 
                    variant="outlined" 
                    size="small" 
                    onChange={(e: any) => setId(e.target.value) }
                />
                <ReturnButton onClick={(e) => returnFilmById()}>반납</ReturnButton>
            </p>
            <Link to="/"><StyledButton>Back</StyledButton></Link>
        </div>
    );
}

export default Return;