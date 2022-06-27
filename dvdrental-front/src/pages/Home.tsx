import React from 'react';
import { Link } from 'react-router-dom';
import StyledButton from '../components/StyledButton';

const Home = () => {

    return (
        <div style={{ paddingTop: '70px'}}>
            <Link to="/search"><StyledButton>검 색</StyledButton></Link>
            <Link to="/return"><StyledButton>반 납</StyledButton></Link>
        </div>
    );
}

export default Home;