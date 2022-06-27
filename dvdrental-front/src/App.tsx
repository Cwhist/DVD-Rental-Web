import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import { createGlobalStyle } from 'styled-components';
import PageHeader from './components/PageHeader';
import PageTemplate from './components/PageTemplate';
import Home from './pages/Home'
import Return from './pages/Return';
import Search from './pages/Search';

const GlobalStyle = createGlobalStyle`
	body {
		background: #e9ecef;
	}
`;

const App = () => {
  return (
    <>
      <GlobalStyle />
      <PageTemplate>
        <PageHeader />
        <Routes>
          <Route path="/" element={<Home/>} />
          <Route path="/search" element={<Search/>} />
          <Route path="/return" element={<Return/>} />
          <Route
            path="*"
            element={<Navigate to="/" />}
          />
        </Routes>
      </PageTemplate>
    </>
  );
}

export default App;
