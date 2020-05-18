import React from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Login from "./component/Login";
import GameSelectView from "./component/GameSelectView";
import GamePlayView from "./component/GamePlayView";

function App() {
  return (
    <>
      <Router>
        <Route path="/Login" component={Login} />
        <Route path="/GameSelectView/:color" component={GameSelectView} />
        <Route path="/GamePlayView/:gameId/:teamId" component={GamePlayView} />
      </Router>
    </>
  );
}

export default App;

// render={() => <Login color={backgroundColor} />}
