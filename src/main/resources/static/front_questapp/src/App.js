import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from './components/Home/Home';
import USer from './components/User/USer';
import Navbar from './components/Navbar/Navbar';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route exact path="/users/:userId" element={<USer />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}
//client side bir rooting yapar server side a gitmez singlepageapp mantigi budur
export default App;
