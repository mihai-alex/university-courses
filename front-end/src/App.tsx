import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom"; // Switch is replaced by Routes in React Router 6
import Navbar from "./components/Navbar";
import ProfessorViewAll from "./components/professor/ProfessorViewAll";
import Homepage from "./components/Homepage";
import ProfessorAdd from "./components/professor/ProfessorAdd";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <div className="container">
          <Routes>
            <Route path="/" element={<Homepage />}></Route>
            <Route path="/professors" element={<ProfessorViewAll />}></Route>
            <Route
              path="/professors/add-professor"
              element={<ProfessorAdd />}
            ></Route>
          </Routes>
        </div>
      </Router>
    </div>
  );
}

export default App;
