import React from "react";

import { cn } from "@/lib/utils";

const Wrapper: React.FC<LayoutProps> = ({ children, className }) => {
  return <div className={cn("w-full min-h-screen", className)}>{children}</div>;
};

export default Wrapper;
