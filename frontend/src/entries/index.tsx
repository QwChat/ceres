import * as React from "react";
import * as ReactDOM from "react-dom";

import { Register, Login } from "./../services/user"

Register({
  nickname: "chenhuan",
  password: "123456"
})

Login({
  nickname: "chenhuan",
  password: "123456"
})

interface IndexProps {}

interface IndexState {}

class Index extends React.Component<IndexProps, IndexState> {
  public render(): JSX.Element {
    return <span>Index</span>;
  }
}

ReactDOM.render(<Index />, document.getElementById("reactApp"));
