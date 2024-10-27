"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

import Container from "@/components/layouts/container";
import Wrapper from "@/components/layouts/wrapper";
import RetroGrid from "../ui/retro-grid";

export default function ActivateAccountPage({ token }: { token: string }) {
  const baseUrl = process.env.NEXT_PUBLIC_BASE_API_URL;
  const router = useRouter();
  const [status, setStatus] = useState("Verifying...");

  useEffect(() => {
    async function verifyToken() {
      try {
        const response = await fetch(`${baseUrl}/auth/verify-user/${token}`, {
          method: "PATCH",
          headers: {
            "Content-Type": "application/json",
          },
          credentials: "include",
        });

        if (response.ok) {
          setStatus("Verification successful!");

          router.push("/");
        } else {
          setStatus("Verification failed. Please try again.");
        }
      } catch (error) {
        console.error("Error during verification:", error);
        setStatus("An error occurred during verification.");
      }
    }

    verifyToken();
  }, [token, router, baseUrl]);

  return (
    <Container>
      <Wrapper className="w-full h-screen flex justify-center items-center relative">
        <RetroGrid />
        <h4>{status}</h4>
      </Wrapper>
    </Container>
  );
}
