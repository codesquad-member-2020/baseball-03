import React, { useState, useEffect } from "react";
import URL from "../constants/url.js";
import GameSelectSet from "../component/GameSelectSet";
import styles from "./style/GameSelectView.module.css";
import classNames from "classnames/bind";

const cx = classNames.bind(styles);

function GameSelectView(props) {
  const [gameList, setGameList] = useState([]);

  const fetchInitialData = async () => {
    const initialGameList = await fetchJSON(URL.PROD.GAME_LIST_API);
    setGameList(initialGameList.content);
  };

  useEffect(() => {
    fetchInitialData();
  }, []);

  return (
    <div
      className={cx("body")}
      style={{ backgroundColor: "#" + props.match.params.color }}
    >
      <div className={styles.header}>BASEBALL GAME ONLINE</div>
      <div className={styles.listHeader}>게임 목록</div>
      <GameSelectSet gameList={gameList} />
      <div></div>
    </div>
  );
}

const fetchJSON = (url) => {
  return fetch(url).then((response) => {
    return response.json();
  });
};

export default GameSelectView;
