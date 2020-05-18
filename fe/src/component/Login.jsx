import React, { useState, useEffect } from "react";
import styled from "styled-components";
import URL from "../constants/url.js";

const StyledDiv = styled.div`
  height: 100%;
  background: ${(props) => props.color};
  display: flex;
  flex-direction: column;
  color: white;
  position: relative;
  justify-content: center;
  align-items: center;
`;

const StyledButton = styled.button`
  height: 300px;
  width: 300px;
  background-image: url(${(props) => props.logoUrl});
  background-size: 100%;
  background-repeat: no-repeat;
  &:hover {
    animation: rotate_image 10s linear infinite;
    transform-origin: 50% 50%;
  }
  @keyframes rotate_image {
    100% {
      transform: rotate(360deg);
    }
  }
`;

function Login(props) {
  const [randomTeam, setRandomTeam] = useState([]);

  const fetchInitialData = async () => {
    const initialRandomTeam = await fetchJSON(URL.PROD.TEAM_LIST_API);
    setRandomTeam(
      initialRandomTeam.content.teams[
        parseInt(Math.random() * initialRandomTeam.content.teams.length)
      ]
    );
  };

  useEffect(() => {
    fetchInitialData();
  }, []);

  return (
    <StyledDiv color={randomTeam.color}>
      <StyledButton
        logoUrl={randomTeam.url}
        onClick={() =>
          props.history.push(
            `/GameSelectView/${randomTeam.color.substring(1, 7)}`
          )
        }
      />
      <p>로고를 눌러서 로그인을 해주세요!</p>
    </StyledDiv>
  );
}

const fetchJSON = (url) => {
  return fetch(url).then((response) => {
    return response.json();
  });
};

export default Login;
