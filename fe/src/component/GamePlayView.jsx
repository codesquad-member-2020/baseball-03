import React, { useState, useEffect } from "react";
import URL from "../constants/url.js";
import baseBallStadiumImg from "../image/baseBallStadium.jpg";
import styles from "./style/GamePlayView.module.css";
import classNames from "classnames/bind";
import { FcSportsMode } from "react-icons/fc";

const cx = classNames.bind(styles);

function GamePlayView(props) {
  const [initGame, setInitGame] = useState({
    isHome: null,
    team: {
      away: null,
      home: null,
    },
    pitcher: {
      name: null,
    },
    hitter: {
      name: null,
      order: null,
    },
  });

  const [defense, setDefense] = useState({
    isHome: null,
    gameScore: {
      away: null,
      home: null,
    },
    halfInning: {
      round: 1,
      isTop: true,
      isAttack: false,
    },
    pitcher: {
      name: null,
      pitchCount: null,
    },
    hitter: {
      name: null,
      order: null,
      avg: null,
    },
    log: {
      result: null,
      count: {
        OUT: null,
        STRIKE: null,
        BALL: null,
      },
      isOut: null,
      isHit: null,
    },
    base: {
      FIRST: null,
      SECOND: null,
      THIRD: null,
      HOME: null,
    },
  });

  const fetchInitialData = async () => {
    const initialGame = await fetchInit(
      URL.PROD.RESET_API,
      props.match.params.gameId,
      props.match.params.teamId
    );
    setInitGame(initialGame.content);
  };

  const fetchDefenseResult = async () => {
    const defenseResult = await fetchDefense(
      URL.PROD.GAME_API,
      props.match.params.gameId,
      props.match.params.teamId
    );
    setDefense(defenseResult);
  };

  useEffect(() => {
    fetchInitialData();
  }, []);

  return (
    <div className={styles.body}>
      <div className={cx("scoreBox", "box")}>
        <div className={styles.teamWrap}>
          <div className={cx("homeTeam", "titleBox")}>
            <div
              className={styles.isPlayingBox}
              style={{
                backgroundColor: initGame.isHome ? "#f9614e" : "transparent",
              }}
            ></div>
            <span className={styles.homeName}>{initGame.team.home}</span>
            <span className={styles.score}>{defense.gameScore.home}</span>
          </div>
          <div className={cx("awayTeam", "titleBox")}>
            <div
              className={styles.isPlayingBox}
              style={{
                backgroundColor: initGame.isHome ? "transparent" : "#f9614e",
              }}
            ></div>
            <span className={styles.homeName}>{initGame.team.away}</span>
            <span className={styles.score}>{defense.gameScore.home}</span>
          </div>
        </div>
        <div className={styles.halfInning}>
          {defense.halfInning.round}회{defense.halfInning.isTop ? "초" : "말"}
        </div>
        <div className={styles.countWrap}>
          <div className={styles.strike}>
            <span>S</span>
            <span className={styles.circleWrap}>
              <div className={cx("circle", "yellow")}></div>
              <div className={cx("circle", "yellow")}></div>
            </span>
          </div>
          <div className={styles.ball}>
            <span>B</span>
            <span className={styles.circleWrap}>
              <div className={cx("circle", "green")}></div>
              <div className={cx("circle", "green")}></div>
              <div className={cx("circle", "green")}></div>
            </span>
          </div>
          <div className={styles.out}>
            <span>O</span>
            <span className={styles.circleWrap}>
              <div className={cx("circle", "red")}></div>
              <div className={cx("circle", "red")}></div>
            </span>
          </div>
        </div>
      </div>
      <div className={cx("pitcherHitter", "box")}>
        <div className={styles.titleBox}>
          <div>투수</div>
          <div className={styles.phName}>{initGame.pitcher.name}</div>
        </div>
        <div className={styles.titleBox}>
          <div>타자</div>
          <div className={styles.phName}>{initGame.hitter.name}</div>
          <div className={styles.phNum}>#{initGame.hitter.order}</div>
        </div>
      </div>
      <button className={cx("box", "pitchButton")} onClick={fetchDefenseResult}>
        PITCH
      </button>
      <img
        className={cx("stadiumImg")}
        src={baseBallStadiumImg}
        alt="야구 경기장 그림"
      />
      <FcSportsMode className={cx("player", "first")} />
      <FcSportsMode className={cx("player", "second")} />
      <FcSportsMode className={cx("player", "third")} />
      <div className={cx("log", "box")}>
        <div className={styles.hitterWrap}>
          <div>1번 타자 류현진</div>
          <div>1 스트라이크</div>
        </div>
      </div>
    </div>
  );
}

const fetchInit = (url, gameId, teamId) => {
  return fetch(`${url}/games/${gameId}/teams/${teamId}/init`, {
    method: "GET",
  }).then((response) => {
    return response.json();
  });
};

const fetchDefense = (url, gameId, teamId) => {
  return fetch(`${url}/games/${gameId}/teams/${teamId}/`, {
    method: "POST",
  }).then((response) => {
    return response.json();
  });
};

const fetchAttack = (url, gameId, teamId) => {
  return fetch(`${url}/games/${gameId}/teams/${teamId}/`, {
    method: "GET",
  }).then((response) => {
    return response.json();
  });
};

export default GamePlayView;
