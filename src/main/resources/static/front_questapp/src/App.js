import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Switch , Route} from "react-router-dom";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Switch>
          <Route exact path="/" component={Home}></Route>
          <Route exact path="/users/:userId"  component={USer}></Route>

        </Switch>
      </BrowserRouter>
      
    </div>
  );
}

export default App;
