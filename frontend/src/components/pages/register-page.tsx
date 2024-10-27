import React from "react";

import Container from "@/components/layouts/container";
import Wrapper from "@/components/layouts/wrapper";
import { RegisterForm } from "@/components/features/auth";

const RegisterPage = () => {
  return (
    <Container>
      <Wrapper className="flex justify-center items-center py-16">
        <RegisterForm />
      </Wrapper>
    </Container>
  );
};

export default RegisterPage;
