import webpack from "webpack";
import { resolve } from "path";
import UglifyJsPlugin from "uglifyjs-webpack-plugin";
import { CheckerPlugin } from "awesome-typescript-loader";
import MiniCssExtractPlugin from "mini-css-extract-plugin";
import OptimizeCSSAssetsPlugin from "optimize-css-assets-webpack-plugin";
import HtmlWebpackPlugin from "html-webpack-plugin";
import { IsProd, GetAllEntries } from "./helper";

const __DEV__: boolean = !IsProd();
const prodPlugins = [
  new MiniCssExtractPlugin({
    filename: "[name].css",
    chunkFilename: "[id].css"
  })
];
const minimizer = [
  new UglifyJsPlugin({
    cache: true,
    parallel: true,
    sourceMap: true // set to true if you want JS source maps
  }),
  new OptimizeCSSAssetsPlugin({})
];

console.log(__DEV__, process.env.NODE_ENV);

export const webpackConfig: webpack.Configuration = {
  mode: __DEV__ ? "development" : "production",
  entry: GetAllEntries(),
  output: {
    path: resolve(__dirname, "./dist"),
    filename: __DEV__ ? "js/[name].bundle.js" : "js/[name].[hash].bundle.js",
    chunkFilename: __DEV__
      ? "js/[name].chunk.bundle.js"
      : "js/[name].[chunkhash].bundle.js"
  },
  resolve: {
    extensions: [".ts", ".tsx", ".js", ".jsx"]
  },
  optimization: {
    minimizer
  },
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        use: [
          {
            loader: "awesome-typescript-loader",
            options: { }
          }
        ]
      },
      {
        test: /\.scss$/,
        use: [
          __DEV__ ? "style-loader" : MiniCssExtractPlugin.loader,
          {
            loader: "css-loader",
            options: {
              sourceMap: __DEV__
            }
          },
          {
            loader: "sass-loader",
            options: {
              sourceMap: __DEV__
            }
          }
        ]
      },
      {
        test: /\.(png|jpg|jpeg|gif|svg)$/,
        use: [
          {
            loader: "url-loader",
            options: {
              limit: 8192
            }
          }
        ]
      }
    ]
  },
  plugins: [
    new CheckerPlugin(),
    new HtmlWebpackPlugin({
      template: "./src/index.html"
    }),
    new webpack.DefinePlugin({
      __DEV__: __DEV__
    })
  ].concat(__DEV__ ? [] : prodPlugins)
};

if (__DEV__) {
  webpackConfig.devtool = "cheap-module-eval-source-map";
}

export default webpackConfig;
