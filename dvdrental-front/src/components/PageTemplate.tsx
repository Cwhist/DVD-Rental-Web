import React from 'react';
import styled from 'styled-components';


const PageTemplateBlock = styled.div`
	
	width: 70%;
	height: 100%;
	min-height: 512px;
	
	
	position: relative; /* 추후 박스 하단에 추가 버튼을 위치시키기 위한 설정 */
	background: white;
	border-radius: 16px;
	box-shadow: 0 0 16px 0 rgba(0, 0, 0, 0.2);
	
	
	margin: 0 auto; /* 페이지 중앙에 나타나도록 설정 */
	
	
	margin-top: 64px;
	margin-bottom: 32px;
	display: flex;
	flex-direction: column;
	text-align:center;
`;


function PageTemplate({ children }: any) {
	return <PageTemplateBlock>{children}</PageTemplateBlock>;
}


export default PageTemplate;