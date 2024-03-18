import React from "react";
import ReactDOM from "react-dom/client";
import "./App.css";
import App from "./src/App";

const root = ReactDOM.createRoot(document.getElementById("main-container"));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
