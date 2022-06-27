import styled from "styled-components";


const ButtonTemplate = styled.button`
    width: 200px;
    height: 100px;
    border-radius: 50px;
    font-family: 'Noto Sans KR';
    font-size: 22px;
    font-weight: bold;
    color: #7a71b0;
    background: linear-gradient(45deg, #b8e4ff 0%, #a6b6f5 50%, #938ae3 100%);
    border: 3px solid #7a71b0;

    display: inline-block;
    margin: 30px 50px 60px 50px;

    cursor: pointer;
`;

function StyledButton({ children }: any) {
    return <ButtonTemplate>{children}</ButtonTemplate>;
}


export default StyledButton;