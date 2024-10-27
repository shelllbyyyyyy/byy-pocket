import React from "react";

import { cn } from "@/lib/utils";

const Container: React.FC<LayoutProps> = ({ children, className }) => {
  return (
    <main className={cn("max-w-[1440px] mx-auto", className)}>{children}</main>
  );
};

export default Container;
