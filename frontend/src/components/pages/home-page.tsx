import React from "react";

import Container from "@/components/layouts/container";
import Wrapper from "@/components/layouts/wrapper";
import Hero from "../sections/hero";
import Features from "../sections/features";

const HomePage = () => {
  return (
    <Container>
      <Wrapper>
        <Hero />
        <Features />
      </Wrapper>
    </Container>
  );
};

export default HomePage;
