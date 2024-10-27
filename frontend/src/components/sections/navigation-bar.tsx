"use client";

import React, { useEffect } from "react";
import Link from "next/link";

import { Button } from "../ui/button";
import { ThemeToggle } from "../ui/theme-toggle";

const NavigationBar = () => {
  useEffect(() => {
    const handleScroll = () => {
      const header = document.querySelector("header");
      if (window.scrollY > 50) {
        header?.classList.add("scrolled");
        header?.classList.remove("default");
      } else {
        header?.classList.add("default");
        header?.classList.remove("scrolled");
      }
    };

    window.addEventListener("scroll", handleScroll);

    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, []);

  return (
    <header>
      <div className="flex justify-between mx-auto max-w-[1600px] items-center py-5 px-6 sm:px-8 md:px-12 lg:px-16">
        <aside>
          <h1 className="font-bold text-2xl text-main">Byy.Pocket</h1>
        </aside>
        <nav id="navigationbar" className="hidden md:block">
          <ul className="flex gap-10">
            <Link href="/about" className="text-hover">
              About
            </Link>
            <Link href="/docs" className="text-hover">
              Documentation
            </Link>
            <Link href="/feature" className="text-hover">
              Features
            </Link>
          </ul>
        </nav>
        <aside className="flex items-center space-x-5">
          <Link href="/auth/login">
            <Button>Login</Button>
          </Link>
          <Link href="http://localhost:8081/logout">
            <Button>Logout</Button>
          </Link>
          <ThemeToggle />
        </aside>
      </div>
    </header>
  );
};

export default NavigationBar;
