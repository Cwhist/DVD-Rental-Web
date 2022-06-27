import React, { useState } from 'react';
import { 
    IconButton, 
    Table,  
    TableContainer,
    TableBody,
    TableRow, 
    TableCell, 
    Paper,
    Collapse,
    Box,
} from '@mui/material';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import axios from 'axios';
import styled from 'styled-components';
  
const StyledTableRow = styled(TableRow)`
    '&:nth-of-type(odd)': {
      background-color: #555555,
    },
    // hide last border
    '&:last-child td, &:last-child th': {
      border: 0,
    },
`;

const colors = {
    actor: '#9a93c2',
    film: '#dcb3e3',
    inventory: '#b3dae3',
}

type HeaderRowProps = {
    target: SearchTarget,
    children: any,
}

const HeaderRow = ({target, children}: HeaderRowProps) => {
    let color = colors.actor;

    if (target === SearchTarget.ACTOR) 
        color = colors.actor;
    if (target === SearchTarget.FILM || target === SearchTarget.ACTOR_FILM) 
        color = colors.film;
    if (target === SearchTarget.FILM_INVENTORY)
        color = colors.inventory;

    const HeaderRowTemplate = styled(TableRow)`
        background-color: ${color};
    `;
    
    return <HeaderRowTemplate>{children}</HeaderRowTemplate>;
}

const StyledTableCell = styled(TableCell)`
    font-family: Noto Sans Kr;
    text-align: center;
    width: 'auto';  

    color: #64626e;
`;

const RentalButton = styled.button`
    width: 75px;
    height: 30px;
    border-radius: 0.3rem;
    font-family: 'Noto Sans KR';
    font-size: 15px;
    color: #7a71b0;
    background: linear-gradient(45deg, #b8e4ff 0%, #a6b6f5 50%, #938ae3 100%);
    border: 1px solid #7a71b0;

    cursor: pointer;
`;

export enum SearchTarget {
    ACTOR, FILM, ACTOR_FILM, FILM_INVENTORY
}

type SearchResultProps = {
    target: SearchTarget,
    data: any,
}

type DataRowProps = {
    target: SearchTarget,
    rowData: any,
    itemNumber: number,
}

const searchExpansionData = (target: SearchTarget, id: number, setData: any, toggleOpen: any) => {
    
    const requestURI = target === SearchTarget.ACTOR_FILM ? '/api/films/actor-id/' + id 
                    : target === SearchTarget.FILM_INVENTORY ? '/api/inventory/film/' + id : '대상이 잘못됐습니다';

    return new Promise((resolve, reject) => {
        axios
            .get(requestURI)
            .then((response) => {
                resolve(response.data);
                setData(response.data._embedded);
                toggleOpen();
            })
            .catch((response) => {
                reject();
                alert("검색 실패");
            })
    })
}

const rentalFilm = (inventoryId: number) => {
    if(window.confirm("잠시 후에 안내되는 대여번호를 잘 기억해주세요! 반납 시에 입력하셔야 반납이 가능합니다! 대여하시겠습니까?")) {
        const requestURI = '/api/rental';

        return new Promise((resolve, reject) => {
            axios
                .post(requestURI, {inventoryId}) // 여기 문제
                .then((response) => {
                    console.log(response.data);
                    alert("대여번호는 " + response.data.rentalId + " 입니다!");
                    window.location.replace('/');
                })
                .catch((response) => {
                    reject();
                    alert("대여 실패");
                })
        })
    }
}

const getCellProps = (searchTarget: SearchTarget) => {
    if (searchTarget === SearchTarget.ACTOR) 
        return ['번호', '배우명', '작품'];
    if (searchTarget === SearchTarget.FILM || searchTarget === SearchTarget.ACTOR_FILM) 
        return ['번호', '작품명', '줄거리', '상영시간', '가격', '대여일', '대여하기'];
    if (searchTarget === SearchTarget.FILM_INVENTORY)
        return ['번호', '수납번호', '대여여부'];
    
    return [];
}

const DataRow = ({target, rowData, itemNumber}: DataRowProps) => {

    const [data, setData] = useState();
    const [open, setOpen] = useState(false);
    const toggleOpen = () => setOpen(!open);

    if (target === SearchTarget.ACTOR) {
        return (
            <>
            <StyledTableRow key={rowData.actorId}> 
                <StyledTableCell component="th" scope="row">
                    {itemNumber} 
                </StyledTableCell>
                <StyledTableCell>{rowData.firstName + " " + rowData.lastName}</StyledTableCell>
                <StyledTableCell>
                    <IconButton
                        aria-label="expand row"
                        size="small"
                        onClick={() => {
                            if(!open) searchExpansionData(SearchTarget.ACTOR_FILM, rowData.actorId, setData, toggleOpen);
                            else toggleOpen();
                        }}
                    >
                        {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                    </IconButton>
                </StyledTableCell>
            </StyledTableRow>
            <TableRow>
                <StyledTableCell style={{ padding: 0 }} colSpan={12}>
                <Collapse in={open} timeout="auto" unmountOnExit>
                    <Box>
                        <h2 style={{ color: '#d982ca', textAlign: 'center'}}>출연 영화</h2>
                        <DataTable target={SearchTarget.ACTOR_FILM} data={data} />
                        <button style={{ width: '100%', height: '50px', backgroundColor: colors.film, border: '0px' }} onClick={ () => toggleOpen() }><KeyboardArrowUpIcon /></button>
                    </Box>
                </Collapse>
                </StyledTableCell>
            </TableRow>
            </>
        )
    }
    if (target === SearchTarget.FILM || target === SearchTarget.ACTOR_FILM) {
        return (
            <>
            <StyledTableRow key={rowData.filmId}>
                <StyledTableCell component="th" scope="row">
                    {itemNumber++}
                </StyledTableCell>
                <StyledTableCell>{rowData.title}</StyledTableCell>
                <StyledTableCell>{rowData.description}</StyledTableCell>
                <StyledTableCell>{rowData.length}</StyledTableCell>
                <StyledTableCell>{rowData.replacementCost}</StyledTableCell>
                <StyledTableCell>{rowData.rentalDuration}</StyledTableCell>
                <StyledTableCell>
                    <IconButton
                        aria-label="expand row"
                        size="small"
                        onClick={() => {
                            if(!open) searchExpansionData(SearchTarget.FILM_INVENTORY, rowData.filmId, setData, toggleOpen);
                            else toggleOpen();
                        }}
                    >
                        {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                    </IconButton>
                </StyledTableCell>
            </StyledTableRow>
            <TableRow>
                <StyledTableCell style={{ padding: 0 }} colSpan={12}>
                <Collapse in={open} timeout="auto" unmountOnExit>
                    <Box>
                        <h2 style={{ color: '#82d2d9', textAlign: 'center' }}>DVD 대여 현황</h2>
                        <DataTable target={SearchTarget.FILM_INVENTORY} data={data} />
                        <button style={{ width: '100%', height: '50px', backgroundColor: colors.inventory, border: '0px' }} onClick={ () => toggleOpen() }><KeyboardArrowUpIcon /></button>
                    </Box>
                </Collapse>
                </StyledTableCell>
            </TableRow>
            </>
        )
    }
    if (target === SearchTarget.FILM_INVENTORY) {
        const rentableMsg = () => 
                <div style={{ color: rowData.rentable ? '#4e6fb5' : '#b54e4e' }}>
                { 
                    rowData.rentable ? 
                        (<RentalButton onClick={() => rentalFilm(rowData.inventoryId)} >대여가능</RentalButton>) 
                        : '대여불가'
                }
                </div>
        return (
            <StyledTableRow key={rowData.inventoryId}>
                <StyledTableCell component="th" scope="row">
                    {itemNumber++}
                </StyledTableCell>
                <StyledTableCell>{rowData.inventoryId}</StyledTableCell>
                <StyledTableCell>{rentableMsg()}</StyledTableCell>
            </StyledTableRow>
        )
    }
    return <React.Fragment />;
}

const DataTable = ({target, data}: SearchResultProps) => {

    let itemNumber = 1;
    const cellProps = getCellProps(target);

    return (
        <TableContainer component={Paper} style={{ width: '98%', margin: '0 auto', marginTop: '10px', marginBottom: '10px', borderRadius: '10px' }}>
            
            <Table sx={{ minWidth: 700 }}>
                <HeaderRow target={target}>
                {
                    cellProps.map((cell) => (
                        <StyledTableCell style={{ whiteSpace: 'nowrap', fontWeight: 'bold', color: '#5d519c' }}>{cell}</StyledTableCell>
                    ))
                }
                </HeaderRow>
                <TableBody>
                {
                    target === SearchTarget.ACTOR ?
                    data.actors.map(( actor: any ) => <DataRow target={target} rowData={actor} itemNumber={itemNumber++} />)
                    : 
                    target === SearchTarget.FILM || target === SearchTarget.ACTOR_FILM ?
                    data.films.map(( film: any ) => <DataRow target={target} rowData={film} itemNumber={itemNumber++} />)
                    : 
                    // target === SearchTarget.FILM_INVENTORY
                    data.inventories.map(( inventory: any ) => <DataRow target={target} rowData={inventory} itemNumber={itemNumber++} />)
                }
                </TableBody>
            </Table>

        </TableContainer>
    )
    
}

const SearchResult = ({target, data}: SearchResultProps) => {
    
    return (
        <DataTable target={target} data={data} />
    );
}


export default SearchResult;