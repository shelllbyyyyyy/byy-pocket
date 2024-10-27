import React from "react";

import Container from "@/components/layouts/container";
import Wrapper from "@/components/layouts/wrapper";
import { LoginForm } from "@/components/features/auth";

const LoginPage = () => {
  return (
    <Container>
      <Wrapper className="flex justify-center items-center py-16 px-2">
        <LoginForm />
      </Wrapper>
    </Container>
  );
};

export default LoginPage;
