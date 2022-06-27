import React from 'react';
import styled from 'styled-components';

const PageHeadBlock = styled.div`
	
	padding-top: 48px;
	padding-left: 32px;
	padding-right: 32px;
	padding-bottom: 24px;
	border-bottom: 1px solid #e9ecef;
    text-align: center;
	background-color: #9a93c2;
	border-radius: 16px 16px 0 0;
	
	h1 {
		margin: 0
		font-size: 36px;
		color: #5d519c;
	}
	
`;

function PageHead() {
	
	return (
		<PageHeadBlock>
            <h1>DVD Rental with React</h1>
		</PageHeadBlock>
	);
}


export default PageHead;