import webpack from "webpack";
import express from "express";
import webpackMiddleware from "webpack-dev-middleware";
import webpackHotMiddleware from "webpack-hot-middleware";
import config from "./webpack.config";
import { IsProd } from "./helper";

const app = express();
const port = 3000;
const compiler = webpack(config);

if (IsProd()) {
  compiler.run(err => {
    console.log(err);
  });
} else {
  const middleware = webpackMiddleware(compiler, {
    publicPath: config.output ? config.output.publicPath : "",
    contentBase: "src",
    stats: {
      colors: true,
      hash: false,
      timings: true,
      chunks: false,
      chunkModules: false,
      modules: false
    }
  });

  app.use(middleware);
  app.use(webpackHotMiddleware(compiler));

  app.listen(port, "0.0.0.0", function onStart(err) {
    if (err) {
      console.log(err);
    }
    console.info(
      "==> 🌎 Listening on port %s. Open up http://0.0.0.0:%s/ in your browser.",
      port,
      port
    );
  });
}
