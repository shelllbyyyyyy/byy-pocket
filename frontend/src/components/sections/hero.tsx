import React from "react";
import { Button } from "../ui/button";
import RetroGrid from "../ui/retro-grid";

const Hero = () => {
  return (
    <section
      id="hero"
      className="flex flex-col justify-center items-center h-[500px] w-full relative gap-10"
    >
      <RetroGrid />
      <div className="text-center space-y-1">
        <h1 className="text-5xl">Welcome to Byy.Pocket</h1>
        <p>Start using this app for better management money</p>
      </div>
      <div className="flex justify-center items-center gap-5 w-full">
        <Button>Get Started</Button>
        <Button variant={"neutral"}>Learn more !</Button>
      </div>
    </section>
  );
};

export default Hero;
