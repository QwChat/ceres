import { resolve, basename, extname } from "path";
import { sync } from "glob";

import { Entry } from "webpack";

export function GetAllEntries(): Entry {
  const files: string[] = sync(resolve(__dirname, "./src/entries/*.tsx"));
  const entry: Entry = {};

  files.forEach(file => {
    const name = basename(file, extname(file));
    entry[name] = [file];
  });

  return entry;
}

export function IsProd(): boolean {
  return process.env.NODE_ENV === "production";
}
