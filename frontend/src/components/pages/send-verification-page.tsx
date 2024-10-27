"use client";

import React, { useState, useEffect, use } from "react";
import { useRouter } from "next/navigation";

import Wrapper from "@/components/layouts/wrapper";
import Container from "@/components/layouts/container";
import { Button } from "@/components/ui/button";
import RetroGrid from "@/components/ui/retro-grid";

const SendVerificationPage = ({ email }: { email: string }) => {
  const router = useRouter();
  const [counter, setCounter] = useState(60);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    if (counter === 0) {
      setIsLoading(false);
      return;
    }

    const timer = setInterval(() => {
      setCounter((prevCounter) => prevCounter - 1);
    }, 1000);

    return () => clearInterval(timer);
  }, [counter]);

  const resendVerification = async () => {
    const baseUrl = process.env.NEXT_PUBLIC_BASE_API_URL;
    try {
      await fetch(`${baseUrl}/auth/resend-verification/${email}`, {
        method: "POST",
      });

      router.refresh();

      setIsLoading(true);
      setCounter(60);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <Container>
      <Wrapper className="flex flex-col justify-center items-center py-16 gap-3 h-screen relative">
        <RetroGrid />
        <h4>Please check your email to activate your account</h4>
        <p className="mt-4 text-gray-600">
          Resend verification in{" "}
          <span className="font-semibold">{counter}</span> seconds.
        </p>
        <Button disabled={isLoading} onClick={resendVerification}>
          Resend Verification
        </Button>
      </Wrapper>
    </Container>
  );
};

export default SendVerificationPage;
