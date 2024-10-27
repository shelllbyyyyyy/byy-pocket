import Link from "next/link";
import React from "react";

const Footer = () => {
  return (
    <footer
      id="footer"
      className="flex justify-center items-center w-full h-20"
    >
      <div className="max-w-[1440px] mx-auto">
        Released under MIT License. The source code is available on{" "}
        <Link
          target="_blank"
          href="https://github.com/shelllbyyyyyy/byy-pocket"
          className="font-heading underline"
        >
          Github
        </Link>
        .
      </div>
    </footer>
  );
};

export default Footer;
