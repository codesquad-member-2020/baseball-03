import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";

const StyledUl = styled.ul`
  overflow: scroll;
  &::-webkit-scrollbar {
    display: none;
  }
  margin-top: 10px;
  height: 520px;
`;

const StyledList = styled.li`
  font-size: x-large;
  margin-top: 20px;
  background-color: rgba(255, 255, 255, 0.5);
  width: 300px;
  height: 80px;
  padding: 10px;
  border-radius: 15px;
`;

const StyledGameWrapper = styled.div`
  height: 80px;
`;

const StyledButtonWrapper = styled.div`
  display: flex;
  justify-content: center;
`;

const StyledButton = styled.button`
  height: 70px;
  width: 70px;
  margin: 0 30px;
  background-image: url(${(props) => props.logoUrl});
  background-size: 100%;
  background-repeat: no-repeat;
  &:hover {
    /* opacity: 0.5; */
    -webkit-filter: drop-shadow(0 5px 10px rgba(0, 0, 0, 0.5));
    -moz-filter: drop-shadow(0 5px 10px rgba(0, 0, 0, 0.5));
    -ms-filter: drop-shadow(0 5px 10px rgba(0, 0, 0, 0.5));
    -o-filter: drop-shadow(0 5px 10px rgba(0, 0, 0, 0.5));
    filter: drop-shadow(0 5px 10px rgba(0, 0, 0, 0.5));
  }
`;

const StyledP = styled.p`
  line-height: 50px;
`;

function GameSelectSet({ gameList = [] }) {
  const gameSet = gameList.map((game) => (
    <>
      <StyledList>
        <StyledGameWrapper>
          <p>GAME {game.matchId}</p>
          <StyledButtonWrapper>
            <Link to={`/GamePlayView/${game.matchId}/${game.home.id}`}>
              <StyledButton logoUrl={game.home.thumbnail_url} />
            </Link>
            <StyledP>VS</StyledP>
            <Link to={`/GamePlayView/${game.matchId}/${game.away.id}`}>
              <StyledButton logoUrl={game.away.thumbnail_url} />
            </Link>
          </StyledButtonWrapper>
        </StyledGameWrapper>
      </StyledList>
    </>
  ));

  return <StyledUl>{gameSet}</StyledUl>;
}

export default GameSelectSet;
